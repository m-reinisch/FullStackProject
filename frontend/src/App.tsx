import './App.css'
import type {Todo, TodoDTO} from "./types.tsx";
import NavBar from "./NavBar.tsx";
import Home from "./Home.tsx";
import Canvas from "./Canvas.tsx";
import NewTodo from "./NewTodo.tsx";
import {Route, Routes} from "react-router-dom";
import {useEffect, useState} from "react";
import axios from 'axios';
import EditTodo from "./EditTodo.tsx";
import ProtectedRoutes from "./ProtectedRoutes.tsx";

function login() {
    const host = window.location.host === 'localhost:5173' ?
        'http://localhost:8080': window.location.origin

    window.open(host + '/oauth2/authorization/github', '_self')
}

function App() {
    const [todos, setTodos]= useState<Todo[]>([])
    const [change, setChange]= useState<number>(0)
    const [user, setUser] = useState<string | null | undefined>(undefined)

    function loadAllTodos(){
        axios.get("/api/todo")
            .then( (response) =>
                setTodos(response.data))
            .catch( (error_) => console.log(error_) )
    }
    function addTodo(desc: string, stat: string){
        const newTodo: TodoDTO= {
            description: desc,
            status: stat
        }

        axios.post("/api/todo", newTodo)
            .then( () => changed() )
            .catch( (error_) => console.log(error_) )
    }
    function changed(){
        setChange(change + 1)
    }
    const loadUser = () => {
        axios.get('/api/auth/me')
             .then(response => {
                setUser(response.data)
             })
            .catch( () => {
                setUser(null)
            })
    }

    useEffect(() => {
        loadUser();
        loadAllTodos()
    }, [change]);

    return (
        <>
            <header>
                <h1>Todo-App</h1>
                <NavBar />
            </header>
            <Routes>
                <Route path={"/"} element={<Home onLogin={login} />} />
                <Route element={<ProtectedRoutes user={user} />}>
                    <Route path={"/todos"}
                           element={<Canvas cTodos={todos} change={changed} />} />
                    <Route path={"/todo/add"}
                           element={<NewTodo submitTodo={addTodo} />} />
                    <Route path={"/todo/edit/:id"}
                           element={<EditTodo change={changed} />} />
                </Route>

            </Routes>
        </>
    )
}

export default App
