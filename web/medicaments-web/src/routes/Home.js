import { Link } from "react-router-dom";
import axios from "axios";

const Home = () => {
    return (
        <div className="m-3 p-3">
            <h2 className="bg-light text-center m-3 p-3 border">MEDICAMENTOS</h2>
            <Link to="/classificationatc">
                <button className="container text-center btn btn-secondary p-5 text-white pointer m-2">CLASSIFICAÇÃO ATC</button>
            </Link>
        </div>
    )
}

const save = () => {
    axios.post("http://localhost:8080/atc/save", {}, {
        auth: {
            username: 'admin',
            password: 'password'
        }
    }).then(response => {
        console.log(response.data)
        window.location.reload()
    }).catch(error => console.log(error))
}

export default Home;