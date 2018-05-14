import React from "react";
import ReactDOM from 'react-dom';

class RootRoutes extends React.Component{

    componentDidMount(){
        console.log("did mount");
    }

    render(){
        return(
            <h1 style={{textAlign:"center"}}> hello </h1>
        )
    }
}

export default RootRoutes