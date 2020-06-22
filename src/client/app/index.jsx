import React from 'react';
import {render} from 'react-dom';
import User from './user.jsx';

class App extends React.Component{
    constructor(props) {
        super(props);
        this.state = { loggedIn:false, autoLogin: true, showLoadingScreen: false };
        this.userLogin = this.userLogin.bind(this);
    }

    userLogin(response){
        console.log(response);

        var loginSuccessful = response["id"]!=undefined;
        if(loginSuccessful){
            if(this.state.autoLogin){
                this.setState({autoLogin: false});
                return;
            }
        }
        else {

            var user = {};
            user["username"] = response["username"];
            user["password"] = response["password"];

            var data = JSON.stringify(user);
            this.setState({showLoadingScreen: true});
            fetch(Context+"/user/login", {
               headers: {
                   'Context-Type': 'application/json',
                   'Accept': 'application/json'
               },
                method: 'post',
                body: data
            })
            .then(this.status)
            .then(this.parseJson)
            .then(this.handleResponse.bind(this))
        }
    }

    loginCallBack(){
        let username = document.getElementById("username").value;
        let password = document.getElementById("password").value;

        this.userLogin({username: username, password: password});

    }

    status(response) {
        if (response.status >= 200 && response.status < 300) {
            return Promise.resolve(response)
        } else {
            this.setState({loggedIn:false, autoLogin: true, showLoadingScreen: false});
            return Promise.reject(new Error(response.statusText))
        }
    }

    parseJson(response) {
        return response.json();
    }

    handleResponse(response) {
        console.log("Response:");
        console.log(response);
        if(response["success"]) {
            User.user = response["user"];
            this.setState({loggedIn:true, showLoadingScreen: false});
            return;
        }
        this.setState({loggedIn:false});
    }

    showLoadingScreen() {
        return <div className={styles.spinnerContainer}>
            <div className={styles.spinner}>
                <div className={styles.rect1}></div>
                <div className={styles.rect2}></div>
                <div className={styles.rect3}></div>
                <div className={styles.rect4}></div>
                <div className={styles.rect5}></div>
            </div>
            <div>Logging you in...</div>
        </div>;
    }

    render () {
        if(this.state.showLoadingScreen) {
            return <div>
                {this.showLoadingScreen()}
            </div>;
        }
        else {
            if(this.state.autoLogin) {
                return (
                    <div style={{"display":"none"}}>

                        <h3>Username</h3>
                        <input type="text"
                                id="username"
                        />
                        <h3>Password</h3>
                        <input type="password"
                                id="password"
                        />
                        <input type="button"
                               onClick="loginCallBack"
                        />
                    </div>
                );
            } else {
                return (
                    <div style={{"textAlign":"center", "marginTop":"50px"}}>
                        <div style={{"width":"400px", "margin": "0 auto"}}>
                            <h2 style={{"textAlign": "left", "marginBottom":"40px"}}>Login via:</h2>
                            <br/><br/>
                            <h3>Username</h3>
                            <input type="text"
                                   name="Username"
                            />
                            <h3>Password</h3>
                            <input type="password"
                                   name="Password"
                            />
                            <input type="button"
                                   onClick="loginCallBack"
                            />
                        </div>
                    </div>
                );
            }
        }

    }
}

render(<App/>, document.getElementById('app'));

module.hot.accept();
