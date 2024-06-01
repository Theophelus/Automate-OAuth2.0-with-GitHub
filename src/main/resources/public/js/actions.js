document.addEventListener('DOMContentLoaded', function(){
    //get the list of row
    var tableRows = document.querySelectorAll('.collapsible-row');
    // loop through the list
    for(let i=0; i < tableRows.length; i++){
       (function (currentRow){
//            currentRow = tableRows[i];
            //listen to each action
            currentRow.addEventListener('click', function() {
            var nextRow = currentRow.nextElementSibling;
                if (nextRow && nextRow.classList.contains('hidden-row')) {
                    nextRow.style.display = nextRow.style.display === 'table-row' ? 'none' : 'table-row';
                }
           });
       })(tableRows[i]);
    }
})