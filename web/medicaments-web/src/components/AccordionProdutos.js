import { Component } from 'react';
import Accordion from 'react-bootstrap/Accordion';

class AccordionProdutos extends Component {

    constructor(props) {
        super();

    }

    render() {
        return (
            <div>
                {
                    Array.from(this.props.data).map((field, index) => (

                        <label className="container border m-1 p-2 bg-light" key={index}>{`${field.codeAtc} - ${field.name}`}</label>

                    ))
                }
            </div>
        );
    }


}


export default AccordionProdutos;