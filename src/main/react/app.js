import React,{ Component } from 'react';
import ReactDOM from 'react-dom';
import RootRoutes from './router';

const main = document.getElementById("main");
ReactDOM.render(
    <RootRoutes />
    ,
    main
);