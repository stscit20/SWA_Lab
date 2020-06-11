import React from 'react';
import User from './user.jsx';

class App extends React.Component{
    constructor(props) {
        super(props);
        this.state = { loggedIn:false, autoLogin: true, showLoadingScreen: false };
    }
}