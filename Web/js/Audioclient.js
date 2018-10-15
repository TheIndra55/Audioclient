console.log("This audioclient is publicly available at https://github.com/TheIndra55/Audioclient")

var callbacks = [];
var params;
var tokens;

function getQueryParams() {
    var qs = document.location.search.split('+').join(' ');
    params = {},
        tokens,
        re = /[?&]?([^=]+)=([^&]*)/g;
    while (tokens = re.exec(qs)) {
        params[decodeURIComponent(tokens[1])] = decodeURIComponent(tokens[2]);
    }
}

function Audioclient(address){
	
	getQueryParams();
	this.u_params = params;
	this.ws = new WebSocket(address);
	
	// latency is not realtime and only checked when messages are send
	this.latency = 0;
	
	this.ws.onmessage = function(message){	
		var json = JSON.parse(message.data);
	
		var calls = callbacks.filter(x => x.type == json.type);
		
		for (var i = 0; i < calls.length; i++) {
			calls[i].callback(json.message);
		}
		
		this.latency = new Date() - new Date(json.now);
	}
	
	this.send = function(type, message){
		this.ws.send(JSON.stringify({type: type, message: message}));
	}
	
	this.on = function(type, callback){
		callbacks.push({type: type, callback: callback});
	}
}