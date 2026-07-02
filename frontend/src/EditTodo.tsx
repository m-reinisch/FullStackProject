import {useNavigate, useParams} from "react-router-dom";
import {useForm} from "react-hook-form";
import {useState} from "react";
import type {Todo} from "./types.tsx";
import axios from "axios";

type FormValues= {
    description: string,
    status: "OPEN" | "IN_PROGRESS" | "DONE"
}
type EditTodoProps= {
    eTodo: Todo,
    change: () => void
}

export default function EditTodo(props: Readonly<EditTodoProps>) {
    const param= useParams();
    const {register, handleSubmit, formState: {errors, isValid}} =
        useForm<FormValues>({mode: "onChange"})
    const nav= useNavigate();
    const [description, setDescription]= useState(props.eTodo.description)

    function onSubmit(data: FormValues){
        const upTodo: Todo = {
            id: param.id,
            description: data.description,
            status: data.status
        }
        axios.put("/api/todo/" + param.id, upTodo)
            .then( () => props.change() )
            .catch( (errors) => console.log(errors) )
        nav("/todos")
    }

    return(
        <div>
            <h3>Bearbeiten:</h3>
            <form onSubmit={handleSubmit(onSubmit)}>
                <label>
                    Beschreibung:
                    <input type={"text"}
                           value={description}
                           onChange={
                                (event) =>
                                    setDescription(event.target.value)
                            }
                           {...register("description",
                                   {
                                       required: "Description is required.",
                                       minLength: {
                                           value: 5,
                                           message: "Description must be at least 5 characters long."}
                                   }
                               )
                           }
                    />
                    {errors.description && <p id="err">{errors.description.message}</p>}
                </label>
                <br />
                <label>
                    Status:
                    <select
                        {...register("status", {
                                required: "Status is required.",
                                validate:
                                    (val) => {
                                        if (val === "OPEN" ||
                                            val === "IN_PROGRESS" ||
                                            val === "DONE") {
                                            return true
                                        } else {
                                            return "Must be OPEN, IN_PROGRESS or DONE"
                                        }
                                    }
                        })}>
                        <option value={props.eTodo.status}>
                            {props.eTodo.status}
                        </option>
                        <option value="OPEN">offen</option>
                        <option value="IN_PROGRESS">in arbeit</option>
                        <option value="DONE">beendet</option>
                    </select>
                    {errors.status && <p id="err">{errors.status.message}</p>}
                </label>
                <br />
                <button type={"submit"} disabled={!isValid}>
                    Änderungen speichern
                </button>
            </form>
        </div>
    )
}