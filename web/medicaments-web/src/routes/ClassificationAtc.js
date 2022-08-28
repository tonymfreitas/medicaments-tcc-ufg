import { Component } from "react";
import AccordionAtc from "../components/AccordionAtc";
import axios from "axios";

class ClassificationAtc extends Component {

    constructor(props) {
        super(props);
        this.state = { data: [], medicaments: [] };

        this.getClassificationAtc()
        this.getMedicaments()
    }

    render() {

        return (
            <div className="container m-5">
                 <h2 className="bg-light text-center m-3 p-3 border">CLASSIFICAÇÃO ATC</h2>
                <AccordionAtc data={this.state.data} medicaments={this.state.medicaments} />
            </div>
        );
    }

    getClassificationAtc = () => {

        axios.get("http://localhost:8080/atc", {
            auth: {
                username: 'admin',
                password: 'password'
            }
        }).then(response => {
            this.setState({ ...this.state, data: response.data })
        }).catch(error => console.log(error))

    }

    getMedicaments = () => {

        axios.get("http://localhost:8080/medicament", {
            auth: {
                username: 'admin',
                password: 'password'
            }
        }).then(response => {
            console.log('Atualizando state com medicamentos')
            this.setState({ ...this.state, medicaments: response.data })
        }).catch(error => console.log(error))

    }

}


export default ClassificationAtc;

