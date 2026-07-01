import './App.css'
import type {Todo, TodoDTO} from "./types.tsx";
import NavBar from "./NavBar.tsx";
import Home from "./Home.tsx";
import Canvas from "./Canvas.tsx";
import NewTodo from "./NewTodo.tsx";
import {Route, Routes } from "react-router-dom";
import {useEffect, useState} from "react";
import axios from 'axios';
import EditTodo from "./EditTodo.tsx";

function App() {
    const [todos, setTodos]= useState<Todo[]>([])
    const [change, setChange]= useState<number>(0)

    function loadAllTodos(){
        axios.get("/api/todo")
            .then( (response) =>
                setTodos(response.data))
            .catch( (errors) => console.log(errors) )
    }
    function addTodo(desc: string, stat: string){
        const newTodo: TodoDTO= {
            description: desc,
            status: stat
        }

        axios.post("/api/todo", newTodo)
            .then( () => changed() )
            .catch( (errors) => console.log(errors) )
    }
    function changed(){
        setChange(change + 1)
    }

    useEffect(() => {
        loadAllTodos()
    }, [change]);

    return (
        <>
            <header>
                <h1>Todo-App</h1>
                <NavBar />
            </header>
            <Routes>
                <Route path={"/"} element={<Home />} />
                <Route path={"/todos"}
                       element={<Canvas cTodos={todos} change={changed} />} />
                <Route path={"/todo/add"}
                       element={<NewTodo submitTodo={addTodo} />} />
                <Route path={"/todo/edit/:id"}
                       element={<EditTodo etodos={todos} change={changed} />} />
            </Routes>
        </>
    )
}

export default App
