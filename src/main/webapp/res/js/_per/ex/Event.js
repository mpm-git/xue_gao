var Event =Class({
// eventElement:null,
 events:{},
 initialize:function()
 {
 // this.eventElement = $("COM_ST_J_Q_EVENT");//定义一个form表单
 // if(!this.eventElement.length){
 //  $('body').append(this.eventElement);//将表单放置在web中
 // }
 },
 bind:function(event,listener)
 {
  this.events[event]=this.events[event]||[];
  if(this.events[event] instanceof Array)
  {
   this.events[event].push(listener);
  }
 },
 trigger:function(event,target){
  if(this.events[event])
  {
   for(var i in this.events[event])
   {
    this.events[event][i].call(target);
   }
  }
  
 }
});