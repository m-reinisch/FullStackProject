import {useForm} from "react-hook-form";
import {useNavigate} from "react-router-dom";

type FormValues= {
    description: string,
    status: "OPEN" | "IN_PROGRESS" | "DONE"
}
type NewTodoProps= {
    submitTodo: (desc: string, stat: string) => void
}

export default function NewTodo({submitTodo}: NewTodoProps) {
    const {register, handleSubmit, formState: {errors, isValid}} =
        useForm<FormValues>({mode: "onChange"})
    const nav= useNavigate();

    function onSubmit(data: FormValues){
        submitTodo(data.description, data.status)
        nav("/todos")
    }

    return(
        <div className="page">
            <h3>Neue Aufgabe anlegen:</h3>
            <form onSubmit={handleSubmit(onSubmit)}>
                <label>
                    Beschreibung:
                    <input type={"text"}
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
                    <input type={"text"}
                           {...register("status", {
                                   required: "Status is required.",
                                   validate:
                                       (val) =>
                                       {
                                           if(val === "OPEN" ||
                                               val === "IN_PROGRESS" ||
                                               val === "DONE"){
                                               return true
                                           } else {
                                               return "Must be OPEN, IN_PROGRESS or DONE"
                                           }
                                        }
                    })} />
                    {errors.status && <p id="err">{errors.status.message}</p>}
                </label>
                <br />
                <button type={"submit"} disabled={!isValid}>
                    Anlegen
                </button>
            </form>
        </div>
    )
}