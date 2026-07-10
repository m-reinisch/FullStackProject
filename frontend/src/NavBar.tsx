import {Link} from "react-router-dom";

type NavBarProps = {
    user: string | undefined | null,
    onLogout: () => void
}

export default function NavBar(props: Readonly<NavBarProps>) {

    return(
        <div id="navbar">
            <Link to={"/"} className="navigation">Home</Link>
            <Link to={"/todos"} className="navigation">App</Link>
            {props.user && <p id="usr">{props.user}</p>}
            <button onClick={props.onLogout}>Logout</button>
        </div>
    )
}