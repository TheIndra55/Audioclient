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
	
	this.ws.onmessage = function(event){
		//Hacky workaround for recieving data :)
	        (async () => {
  			const blob = new Blob([event.data]);
 		        const buf = await blob.arrayBuffer();
  			var decoded = msgpack.deserialize(buf);

			var calls = callbacks.filter(x => x.type == decoded.type);
		
			for (var i = 0; i < calls.length; i++) {
				calls[i].callback(decoded.message);				}
		
			this.latency = new Date() - new Date(decoded.now);
		})();
	}
	
	this.send = function(type, message){
		var data = {type: type, message: message};
		var bytes = msgpack.serialize(data);
		this.ws.send(bytes);
	}
	
	this.on = function(type, callback){
		callbacks.push({type: type, callback: callback});
	}
}