//use Util
(function(global) {
	
})(window);
function Class () {
	var len = arguments.length;
	var P = arguments[0];
	var F = arguments[len - 1];

	var C = typeof F.initialize == "function" ?
			F.initialize :
			function () {
					if(typeof P.prototype.initialize !="function")
						P.prototype.initialize=function(){};
					P.prototype.initialize.apply(this, arguments);
				};
	if (len > 1) {
		var newArgs = [C, P].concat(Array.prototype.slice.call(arguments).slice(1, len-1), F);
		inherit.apply(null, newArgs);
	} else {
		C.prototype = F;
	}
	return C;
}
/**
 * Function: inherit
 *
 * Parameters:
 * C - {Object} the class that inherits
 * P - {Object} the superclass to inherit from
 *
 * In addition to the mandatory C and P parameters, an arbitrary number of
 * objects can be passed, which will extend C.
 */
function inherit (C, P) {
	var F = function() {};
	F.prototype = P.prototype;
	C.prototype = new F;
	var i, l, o;
	for(i=2, l=arguments.length; i<l; i++) {
		o = arguments[i];
		if(typeof o === "function") {
			o = o.prototype;
		}
		Util.extend(C.prototype, o);
	}
};
/**
 * APIFunction: extend
 * Copy all properties of a source object to a destination object.  Modifies
 *     the passed in destination object.  Any properties on the source object
 *     that are set to undefined will not be (re)set on the destination object.
 *
 * Parameters:
 * destination - {Object} The object that will be modified
 * source - {Object} The object with properties to be set on the destination
 *
 * Returns:
 * {Object} The destination object.
 */
Util=typeof Util=="undefined"?{}:Util;
Util.extend=function (destination, source) {
	destination = destination || {};
	if (source) {
		for (var property in source) {
			var value = source[property];
			if (value !== undefined) {
				destination[property] = value;
			}
		}

		/**
		 * IE doesn't include the toString property when iterating over an object's
		 * properties with the for(property in object) syntax.  Explicitly check if
		 * the source has its own toString property.
		 */

		/*
		 * FF/Windows < 2.0.0.13 reports "Illegal operation on WrappedNative
		 * prototype object" when calling hawOwnProperty if the source object
		 * is an instance of window.Event.
		 */

		var sourceIsEvt = typeof window.Event == "function"
			&& source instanceof window.Event;

		if (!sourceIsEvt
				&& source.hasOwnProperty && source.hasOwnProperty("toString")) {
			destination.toString = source.toString;
		}
	}
	return destination;
};
var NObject=Class({
	initialize:function(){},
	__nid:null,
	get nid(){ 
		if(this.__nid==null)
			this.__nid=this._nid.index++;
		return this.__nid; 
	},
	get windowId(){ 
		window['NObject_'+"nid_"+this.nid]=this;
		return 'NObject_'+"nid_"+this.nid; 
	},
	_nid:{index:0}
});
var Set=Class(NObject,{
	list:[],
	initialize:function()
	{
		
	},
	push:function()
	{
		
	}
}); 