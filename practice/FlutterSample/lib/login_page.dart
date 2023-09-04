import 'package:firebase_auth/firebase_auth.dart';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';

import 'chat_page.dart';
import 'main.dart';
import 'mymodel.dart';

final myModelProvider = Provider((ref) {
  return const MyModel(name: "take", age: 1);
});

class LoginPage extends ConsumerWidget {
  @override
  Widget build(BuildContext context, WidgetRef ref) {
    final infoText = ref.watch(infoTextProvider);
    final email = ref.watch(emailProvider);
    final password = ref.watch(passwordProvider);

    return Scaffold(
      body: Center(
        child: Container(
          padding: const EdgeInsets.all(24),
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: <Widget>[
              Text(ref.watch(myModelProvider).name),
              TextFormField(
                decoration: const InputDecoration(labelText: "メールアドレス"),
                initialValue: "kiyohiro0928@gmail.com",
                onChanged: (String value) {
                  ref.read(emailProvider.notifier).setEmail(value);
                },
              ),
              TextFormField(
                decoration: const InputDecoration(labelText: "パスワード"),
                obscureText: true,
                initialValue: "murakikiKI1543",
                onChanged: (String value) {
                  ref.read(passwordProvider.notifier).state = value;
                },
              ),
              Container(
                padding: const EdgeInsets.all(8),
                child: Text(infoText.toString()),
              ),
              Container(
                width: double.infinity,
                child: ElevatedButton(
                    child: Text("登録"),
                    onPressed: () async {
                      try {
                        final FirebaseAuth auth = FirebaseAuth.instance;
                        final result = await auth.createUserWithEmailAndPassword(email: email.toString(), password: password);

                        ref.read(userProvider.notifier).state = result.user;

                        await Navigator.of(context).pushReplacement(
                          MaterialPageRoute(builder: (context) {
                            return ChatPage();
                          }),
                        );
                      } catch (e) {
                        ref.read(infoTextProvider.notifier).state = "登録に失敗しました：${e.toString()}";
                      }
                    }),
              ),
              const SizedBox(
                height: 8,
              ),
              Container(
                  width: double.infinity,
                  child: OutlinedButton(
                    child: Text("ログイン"),
                    onPressed: () async {
                      try {
                        final FirebaseAuth auth = FirebaseAuth.instance;
                        final result = await auth.signInWithEmailAndPassword(email: email.toString(), password: password);
                        await Navigator.of(context).pushReplacement(MaterialPageRoute(builder: (context) {
                          return ChatPage();
                        }));
                      } catch (e) {
                        ref.read(infoTextProvider.notifier).state = "ログインに失敗しました：${e.toString()}";
                      }
                    },
                  ))
            ],
          ),
        ),
      ),
    );
  }
}
