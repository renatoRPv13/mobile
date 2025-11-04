
import 'package:flutter/material.dart';

class TodoListBuilder extends StatefulWidget {
  late List<String> todoList;
  late void Function()  updateLocalData;
  TodoListBuilder({super.key, required this.todoList,required this.updateLocalData});

  @override
  State<TodoListBuilder> createState() => _TodoListBuilderState();
}
class _TodoListBuilderState extends State<TodoListBuilder> {

  void onItemClicked({required int index}){
    showModalBottomSheet(
      context: context,
      builder:  (context){
        return Container(
       padding: EdgeInsets.all(20.0),
       child: ElevatedButton(
           onPressed: (){
             setState(() {
               widget.todoList.removeAt(index);
             });
             widget.updateLocalData();
             Navigator.pop(context);
           },
           child: Text("Marcar como concluído!")
       ),   
        );
      }
    );
  }
  @override
  Widget build(BuildContext context) {
    return (widget.todoList.isEmpty)?
    Center(
          child: Text("Parabéns para todas!!",style: TextStyle(
              fontSize: 15.0,color: Colors.pink
          ),),
        )
        :ListView.builder(
      itemCount: widget.todoList.length,
      itemBuilder: (BuildContext context, int index){
        return Dismissible(
            key: UniqueKey(),
          direction: DismissDirection.startToEnd,
          background: Container(
            color: Colors.green,
            child: Row(
               children: [
                 Padding(padding:
                 const EdgeInsets.all(8.0),
                   child: Icon(Icons.check),
                 )
               ],
            ),
          ), onDismissed: (direction){
              setState(() {
                widget.todoList.removeAt(index);
              });
              widget.updateLocalData();
        },
          child: ListTile(
            onTap: (){
            onItemClicked(index: index);
            },
            title: Text(widget.todoList[index]),
          ),

        );
      }
    );
  }
}
