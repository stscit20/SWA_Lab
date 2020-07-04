import React from 'react';
import {render} from 'react-dom';
import User from './user.jsx';
import Company from './company.jsx';
class App extends React.Component{
    constructor(props) {
        super(props);
        this.state = { loggedIn:false, autoLogin: true, showLoadingScreen: false };
        this.userLogin = this.userLogin.bind(this);
       
    }

companyList(response){
			
      	var xhr = new XMLHttpRequest();
	    xhr.open("GET", "http://localhost:8080/SWA_Lab/apiv2/companies/getallcompanies", false);
        xhr.setRequestHeader('Content-type','application/json; charset=utf-8');
        xhr.send();      
	  	console.log(JSON.parse(xhr.response));
	    let responser = JSON.parse(xhr.response);
	    return responser;
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
            var xhr = new XMLHttpRequest();
	    xhr.open("POST", "http://localhost:8080/SWA_Lab/apiv2/user/login", false);
            xhr.setRequestHeader('Content-type','application/json; charset=utf-8');
	    xhr.send(data);
	    console.log(JSON.parse(xhr.response));
	    let responser = JSON.parse(xhr.response);
	    if(responser.success){
		User.user = responser.user;
		this.setState({loggedIn:true, showLoadingScreen: false});
		return;
	    }else{
		this.setState({loggedIn:false, autoLogin: true, showLoadingScreen: false});
	    }
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
        return <div> Und ich Flieg </div>;
    }

    render () {
        if(this.state.showLoadingScreen) {
            return <div>
                {this.showLoadingScreen()}
            </div>;
        }
        else {
        if(this.state.loggedIn){
        
     const datalist= this.companyList().map((item,key) => 
      //  <li key={item.idcompany}>CompanyId:{item.idcompany}</li>
       
     <li key={item.idcompany}>CompanyId:{item.idcompany} 
    						  Address:{item.address} 
    						  CompanyName:{item.companyname}  
        					  Department:{item.department}			</li>
                  
              
     ); 
     console.log(datalist); 
   return(  <div>
         <ul>
         {datalist}
         </ul>
       </div> 
       ); 
    }
   
        else{
            if(this.state.autoLogin) {
                return (
                    <div>

                        <h3>Username</h3>
                        <input type="text"
                                id="username"
                        />
                        <h3>Password</h3>
                        <input type="password"
                                id="password"
                        />
                        <input type="button"
                               onClick={this.loginCallBack.bind(this)}
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
                                   onClick={this.loginCallBack.bind(this)}
                            />
                        </div>
                    </div>
                );
            }
          }
        }

    }
}

render(<App/>, document.getElementById('app'));

module.hot.accept();
