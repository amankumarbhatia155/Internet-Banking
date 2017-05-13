function home(){
 var x,y;
 x=document.getElementById(1).Value;
 y=document.getElementById(2).Value;
 if(x.equals("") || y.equals("") )
 { 
	 alert("Enter Username and password");
 }

 else {
		document.login.action="Login";
	    document.login.submit();
	}
}

function signup() {
	 
	document.login.action="register.jsp";
	document.login.submit();
	
}

function open1() 
{
document.home.action="open.jsp";
document.home.submit();
}


function deposit1()
{
document.home.action="deposit.jsp";
document.home.submit();
}
function deposit()
{
document.home.action="withdraw.jsp";
document.home.submit();
}


function transfer()
{
document.home.action="transfer.jsp";
document.home.submit();
}

function deposit3()
{
document.home.action="close.jsp";
document.home.submit();
}

function login()
{
	alert("Succcesful");
	document.register.action="login.jsp";
	document.register.submit();
}

