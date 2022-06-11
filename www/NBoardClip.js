var exec = require('cordova/exec');


exports.getClipBoardData = function(success, error){
    exec(success, error, 'NBoardClip', 'getClipBoardData', [0]);
};
exports.getClipBoardDataAt = function(pos,success, error){
    if(pos.length != 0){
        exec(success, error, 'NBoardClip', 'getClipBoardData', pos);
    }else{
        error("Invalid Postion");
    }
};