var RaphaelUtil =typeof(RaphaelUtil) =="undefined"?{}:RaphaelUtil;
RaphaelUtil.getPath=function(path,sx,sy){
	if(!sx)
		sx=1;
	if(!sy)
		sy=1;
	var matrix =Raphael.matrix(1, 0, 0, 1,0,0);
	matrix.scale(sx, sy,0, 0);
	
	var _p=Raphael.mapPath(path, matrix);
	return _p;
//	var box=Raphael.pathBBox(path);
////	var sx=w?w/box.width:1;
////	var sy=l?l/box.height:1;
//	var x=box.x;
//	var y=box.y;
//	
////	return Raphael.mapPath(path, matrix);
//	console.info(matrix.toTransformString());
//	return Raphael.transformPath(path, matrix.toTransformString());
};