import type {Todo} from "./types.tsx";
import {TodoCard} from "./TodoCard.tsx";
import { useNavigate } from "react-router-dom";

type CanvasProps= {
    cTodos: Todo[],
    change: () => void
}

export default function Canvas(props: Readonly<CanvasProps>) {
    const nav= useNavigate();

    return(
        <div className="page">
            <table>
                <thead>
                <tr>
                    <th>Open:</th>
                    <th>In progress:</th>
                    <th>Finished:</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>
                        {props.cTodos
                            .filter(t =>
                                t.status === "OPEN")
                            .map(t =>
                                <TodoCard todo={t}
                                          change={props.change}
                                          key={t.id}/>)
                        }
                    </td>
                    <td>
                        {props.cTodos
                            .filter(t =>
                                t.status === "IN_PROGRESS")
                            .map(t =>
                                <TodoCard todo={t} change={props.change} />)
                        }
                    </td>
                    <td>
                        {props.cTodos
                            .filter(t =>
                                t.status === "DONE")
                            .map(t =>
                                <TodoCard todo={t} change={props.change} />)
                        }
                    </td>
                </tr>
                </tbody>
            </table>
            <br />
            <button onClick={
                () => nav("/todo/add")
            }>Add Aufgabe</button>
        </div>
    )
}