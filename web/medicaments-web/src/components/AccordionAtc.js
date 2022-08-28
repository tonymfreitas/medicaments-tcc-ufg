import { Component } from 'react';
import Accordion from 'react-bootstrap/Accordion';
import AccordionProdutos from './AccordionProdutos';

class AccordionAtc extends Component {

    constructor(props) {
        super();
        this.state = {
            data: [],
            medicaments: []
        }
    }

    componentWillReceiveProps(nextProps) {
        this.setState({ data: nextProps.data, medicaments: nextProps.medicaments })
    }


    filterByCodeAtc(codeAtcParent) {
        var medicamentsForFilter = this.state.medicaments || []
        var medicamentsFiltered = Array.from(medicamentsForFilter).filter(med => String(med.codeAtc).startsWith(codeAtcParent))
        return medicamentsFiltered ? medicamentsFiltered : []
    }

    renderChildren = (children, codeAtcParent) => {
        return (
            Array.from(children).length > 0 ? <AccordionAtc data={children} medicaments={this.props.medicaments} /> : <AccordionProdutos data={this.filterByCodeAtc(codeAtcParent)} />
        )
    }

    render() {
        return (
            <Accordion>
                {
                    Array.from(this.props.data).map((field, index) => (
                        <Accordion.Item eventKey={field.codeAtc} key={index}>
                            <Accordion.Header>{`${field.codeAtc} - ${field.name}`}</Accordion.Header>
                            <Accordion.Body>
                                {this.renderChildren(field.children, field.codeAtc)}
                            </Accordion.Body>
                        </Accordion.Item>
                    ))
                }
            </Accordion>
        );
    }


}


export default AccordionAtc;