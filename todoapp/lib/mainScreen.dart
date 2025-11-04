import 'package:flutter/material.dart';
import 'package:todoapp/widgets/todoList.dart';
import 'package:shared_preferences/shared_preferences.dart';
import 'package:url_launcher/url_launcher.dart';
import 'addTodo.dart';

class Mainscreen extends StatefulWidget {
  const Mainscreen({super.key});
  @override
  State<Mainscreen> createState() => _MainscreenState();
}
class _MainscreenState extends State<Mainscreen> {

  List<String> todoList = [];
  void addTodo({required dynamic todoText}) {
    if(todoList.contains(todoText)){
      showDialog(
          context: context,
          builder: (context){
            return AlertDialog(
            title: Text("Já existe"),
             content: Text("Estes dados de tarefas já existem."),
             actions: [
               InkWell(
                 onTap: (){
                   Navigator.pop(context);
                 },
                 child: Text("Fechar"),
               )
             ],
            );
        }
      );
      return;
    }
    setState(() {
      todoList.insert(0, todoText);
    });
  }
void updateLocalData() async {
final SharedPreferences prefs = await SharedPreferences.getInstance();
await prefs.setStringList("todoList", todoList);
}
void loadData() async {
  final SharedPreferences prefs = await SharedPreferences.getInstance();
  todoList = prefs.getStringList("toda lista") ?? [];
  setState(() {});
}
@override
  void initState(){
    loadData();
    super.initState();
}

void showAddTodoBottomSheet() {
showAdaptiveDialog(
    context: context,
    builder: (context){
      return Padding(
          padding: MediaQuery.of(context).viewInsets,
              child: Container(
              padding: EdgeInsets.all(20.0),
              height: 200,
              child: AddTodo(addTodo: addTodo),
      ),
      );
});
}
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        backgroundColor: Colors.black26,
        title: Text("Dia das Mães",
          style: TextStyle(color: Colors.pink,fontSize: 15.0
          ),),
        centerTitle: true,
      ),
      floatingActionButton: FloatingActionButton(
          shape: BeveledRectangleBorder(
            borderRadius: BorderRadius.circular(10.0)
          ),
          backgroundColor: Colors.blueGrey[900],
          onPressed: showAddTodoBottomSheet,
          child: Icon(Icons.add, color: Colors.white)
      ),
      drawer: Drawer(
        child: Column(
          children: [
            Container(
              color: Colors.blueGrey[900],
              height: 200,
              width: double.infinity,
              child: Center(
                child: Text(
                  'Todo App',style: TextStyle(
                  color: Colors.white,fontWeight: FontWeight.bold,fontSize: 20.0
                ),
                ),
              ),
            ),
            ListTile(
                onTap: () {
                  launchUrl(Uri.parse("https://sujanpokhrelstc.netlify.app"));
                },
                leading: Icon(Icons.person),
                title: Text("Sobre mim",
                    style: TextStyle(fontWeight: FontWeight.bold))),
            ListTile(
                onTap: () {
                  launchUrl(Uri.parse("mailto:someone@example.com"));
                },
                leading: Icon(Icons.email),
                title: Text("Entre em contato comigo",
                    style: TextStyle(fontWeight: FontWeight.bold)))
          ],
        ),
      ),
      body: TodoListBuilder(
        todoList: todoList,
        updateLocalData: updateLocalData.toString,
      ),
    );
  }
}

