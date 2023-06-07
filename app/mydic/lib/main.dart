import 'package:flutter/material.dart';
import 'package:mydic/edit_card.dart';

import "package:firebase_auth/firebase_auth.dart";
import "package:firebase_core/firebase_core.dart";
import "firebase_options.dart";

void main() async {
  WidgetsFlutterBinding.ensureInitialized();
  await Firebase.initializeApp(
    options: DefaultFirebaseOptions.currentPlatform,
  );
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(
        primarySwatch: Colors.blue,
        primaryColor: const Color(0xFFFFCA64),
        primaryColorLight: const Color(0xFFffdb97),
        scaffoldBackgroundColor: const Color(0xFFF3F3F7),
      ),
      home: const Home(title: 'MyDic'),
    );
  }
}

class Home extends StatefulWidget {
  const Home({super.key, required this.title});

  final String title;

  @override
  State<Home> createState() => _HomeState();
}

class _HomeState extends State<Home> {
  String _email = "email";
  String _password = "pass";

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Theme.of(context).scaffoldBackgroundColor,
      appBar: AppBar(
        title: Padding(
          padding: const EdgeInsets.only(left: 10),
          child: Text(widget.title),
        ),
        backgroundColor: Theme.of(context).primaryColor,
        shape: const RoundedRectangleBorder(borderRadius: BorderRadius.vertical(bottom: Radius.circular(30))),
        elevation: 2,
        actions: <Widget>[
          Padding(
            padding: const EdgeInsets.only(right: 10),
            child: IconButton(
              icon: const Icon(Icons.settings, color: Colors.white),
              onPressed: () {},
            ),
          )
        ],
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            Text(
              'Hello Flutter',
              style: Theme.of(context).textTheme.headlineMedium,
            ),
            ElevatedButton(
                onPressed: () async {
                  try {
                    final User? user = (await FirebaseAuth.instance.createUserWithEmailAndPassword(email: _email, password: _password)).user;
                    if (user != null) {
                      print("登録完了");
                    }
                  } catch (e) {
                    print(e);
                  }
                },
                child: const Text("Register")),
            ElevatedButton(
                onPressed: () async {
                  try {
                    final User? user = (await FirebaseAuth.instance.signInWithEmailAndPassword(email: _email, password: _password)).user;
                    if (user != null) {
                      print("Login shita");
                    }
                  } catch (e) {
                    print(e);
                  }
                },
                child: const Text("Login")),
            ElevatedButton(
                onPressed: () async {
                  try {
                    (await FirebaseAuth.instance.sendPasswordResetEmail(email: _email));
                    print("send mail");
                  } catch (e) {
                    print(e);
                  }
                },
                child: const Text("Reset pass"))
          ],
        ),
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: () {
          Navigator.push(context, MaterialPageRoute(builder: (context) => const EditCard()));
        },
        tooltip: 'Increment',
        child: const Icon(Icons.add),
      ), // This trailing comma makes auto-formatting nicer for build methods.
    );
  }
}
