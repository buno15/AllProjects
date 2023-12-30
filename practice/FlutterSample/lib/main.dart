import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:firebase_auth/firebase_auth.dart';
import 'package:firebase_core/firebase_core.dart';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:flutter_sample/firebase_options.dart';
import 'package:freezed_annotation/freezed_annotation.dart';

import 'login_page.dart';
import 'mymodel.dart';

final userProvider = StateProvider((ref) {
  return FirebaseAuth.instance.currentUser;
});

final infoTextProvider = StateNotifierProvider.autoDispose((ref) {
  return Info();
});

class Info extends StateNotifier<String> {
  Info() : super("");

  void setInfo(String value) {
    state = value;
  }
}

final emailProvider = StateNotifierProvider.autoDispose((ref) {
  return Email();
});

class Email extends StateNotifier<String> {
  Email() : super("");

  void setEmail(String value) {
    state = value;
  }
}

final passwordProvider = StateProvider.autoDispose((ref) {
  return "";
});

final messageTextProvider = StateNotifierProvider.autoDispose((ref) {
  return Message();
});

class Message extends StateNotifier<String> {
  Message() : super("");

  void setMessage(String value) {
    state = value;
  }
}

final postsQueryProvider = StreamProvider.autoDispose((ref) {
  return FirebaseFirestore.instance.collection("posts").orderBy("date").snapshots();
});

Future<void> main() async {
  // Firebase初期化
  WidgetsFlutterBinding.ensureInitialized();
  await Firebase.initializeApp(options: DefaultFirebaseOptions.currentPlatform);
  runApp(const ProviderScope(child: ChatApp()));
}

class ChatApp extends StatelessWidget {
  const ChatApp({super.key});

  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'ChatApp',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: LoginPage(),
    );
  }
}
