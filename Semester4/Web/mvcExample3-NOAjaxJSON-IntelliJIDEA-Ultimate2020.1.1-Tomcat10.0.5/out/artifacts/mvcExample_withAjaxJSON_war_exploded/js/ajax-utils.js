function getUserDescendents(id, callbackFunction){
    $.post(
        "familyController",
        {action: 'getAll', id: id},
        callbackFunction
    );
}
