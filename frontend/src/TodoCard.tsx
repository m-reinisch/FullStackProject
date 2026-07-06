import type {Todo} from "./types.tsx";
import axios from "axios";
import {Link} from "react-router-dom";

type TodoProps= {
    todo: Todo,
    change: () => void
}

export function TodoCard(props: Readonly<TodoProps>) {
    function levelUp(data: Todo) {
        const level: string = data.status
        let stat: string

        if (level === "OPEN") {
            stat = "IN_PROGRESS"
        } else if (level === "IN_PROGRESS") {
            stat = "DONE"
        } else {
            stat = "DONE"
        }
        const upTodo: Todo = {
            id: data.id,
            description: data.description,
            status: stat
        }
        axios.put("/api/todo/" + data.id, upTodo)
             .then(() => props.change())
             .catch((errors) => console.log(errors))
    }
    function del(id: string){
        axios.delete("/api/todo/" + id)
             .then(() => props.change())
             .catch((errors) => console.log(errors))
    }

    return (
        <div id="card">
            <p id="desc">{props.todo.description}</p>
            <p id="stat">{props.todo.status}</p>
            <Link to={"/todo/edit/" + props.todo.id}>
                Bearbeiten
            </Link>
            <p id="buttons">
                <button id="plus" type={"button"}
                        hidden={
                            props.todo.status === "DONE"
                        }
                        onClick={
                            () => levelUp(props.todo)
                }>Level up
                </button>
                <button id="del" type={"button"}
                        disabled={
                            props.todo.status !== "DONE"
                        }
                        hidden={
                            props.todo.status !== "DONE"
                        }
                        onClick={
                            () => {del(props.todo.id)}
                }>Löschen</button>
            </p>
        </div>
    )
}