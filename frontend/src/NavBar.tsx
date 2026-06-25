import {Link} from "react-router-dom";

export default function NavBar() {

    return(
        <>
            <Link to={"/"} className="navigation">Home</Link>
            <Link to={"/todos"} className="navigation">App</Link>
        </>
    )
}