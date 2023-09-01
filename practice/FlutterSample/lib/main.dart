import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:firebase_auth/firebase_auth.dart';
import 'package:firebase_core/firebase_core.dart';
import 'package:flutter/material.dart';

import 'firebase_options.dart';

Future<void> main() async {
  // Firebase初期化
  WidgetsFlutterBinding.ensureInitialized();
  await Firebase.initializeApp(
    options: DefaultFirebaseOptions.currentPlatform,
  );
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(
        primarySwatch: Colors.blue,
        visualDensity: VisualDensity.adaptivePlatformDensity,
      ),
      home: MyAuthPage(),
    );
  }
}

class MyAuthPage extends StatefulWidget {
  @override
  _MyAuthPageState createState() => _MyAuthPageState();
}

class _MyAuthPageState extends State<MyAuthPage> {
  String newUserEmail = "";
  String newUserPassword = "";
  String loginUserEmail = "";
  String loginUserPassword = "";
  String infoText = "";

  String info = "";

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Center(
        child: Column(
          children: <Widget>[
            ElevatedButton(
                onPressed: () async {
                  await FirebaseFirestore.instance.collection("users").doc("id_abc").set({"name": "スズキ", "age": 40});
                },
                child: Text("コレクション＋ドキュメント作成")),
            ElevatedButton(
              onPressed: () async {
                await FirebaseFirestore.instance
                    .collection("users")
                    .doc("id_abc")
                    .collection("orders")
                    .doc("id_123")
                    .set({"price": 600, "date": "12/12"});
              },
              child: Text("サブコレクション＋ドキュメント作成"),
            ),
            ElevatedButton(
                onPressed: () async {
                  await FirebaseFirestore.instance.collection("users").doc("id_abc").collection("orders").doc("id_123").delete();
                },
                child: Text("ドキュメント一覧取得")),
          ],
        ),
      ),
    );
  }
}
