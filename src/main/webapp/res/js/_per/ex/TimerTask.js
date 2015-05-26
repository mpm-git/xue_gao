var TimerTask = Class(NObject,{
    tasks:{},
    initialize: function (param) {
    },
    setTask: function (taskId,task,param,interval,times)
    {
         //= {};
        if (!this.tasks[taskId]) //||!this.tasks[taskId]["run"])
        {
            this.tasks[taskId] = {};
            this.tasks[taskId]["task"] = task || function () { };
            this.tasks[taskId]["param"] = param;
            this.tasks[taskId]["interval"] = interval > 0 ? interval : 1000;
            this.tasks[taskId]["times"] = (times || 0) > 0 ? times : 0;
        }
    },
    runTask: function (taskId,start)
    {
        if (this.tasks[taskId]&& !this.tasks[taskId]["run"])
        {
            {
               
                if (this.tasks[taskId]["times"] >=0)
                {
                    var f = function (target) {
                        
                        return function () {
                            {
                               
                                target.tasks[taskId]["task"](target.tasks[taskId]["param"]);
                                target.tasks[taskId]["runTimes"] = target.tasks[taskId]["runTimes"] || 0;
                                target.tasks[taskId]["runTimes"]++;
                                target.tasks[taskId]["totalRunTimes"] = (target.tasks[taskId]["totalRunTimes"] || 0)+1;
                                if (target.tasks[taskId]["times"] > 0 && target.tasks[taskId]["runTimes"] < target.tasks[taskId]["times"] &&target.tasks[taskId]["run"]) {
                                    window.setTimeout(function (t) {
                                        return function () {
                                                f();
                                        };
                                       
                                        //arguments.callee();
                                    }(target.tasks[taskId]["totalRunTimes"]), target.tasks[taskId]["interval"]);
                                    // arguments.callee(target);
                                }
                                else if (target.tasks[taskId]["times"] == 0 && target.tasks[taskId]["run"]) {
                                    window.setTimeout(function (t) {
                                        return function () {
 
                                            if (t >= target.tasks[taskId]["totalRunTimes"]-1)
                                                f();
                                        };
                                       // target.tasks[taskId]["run"] = false;
                                    }(target.tasks[taskId]["totalRunTimes"]), target.tasks[taskId]["interval"]);
                                }
                            }
                        };
                    }(this);
                    var a = this;
                    window.setTimeout(function () {
                        // a.tasks[taskId]["stoping"] = false;
                        a.tasks[taskId]["run"] = true;
                        f();
                    }, start>0?start:1);
                }
            }
        }
    },
    stopTask: function (taskId)
    {
        if (this.tasks[taskId]) {
            this.tasks[taskId]["run"] = false;
            this.tasks[taskId]["stopTag"] = true;
        }
    },
    stopTaskOther: function (taskId)
    {
    	for(var i in this.tasks)
    	{
    		console.info(i);
    		if (taskId!=i&&this.tasks[i]) {
    			this.tasks[i]["run"] = false;
    			this.tasks[i]["stopTag"] = true;
    		}
    	}
    }
});