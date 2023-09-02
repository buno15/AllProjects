import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:firebase_auth/firebase_auth.dart';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:flutter_sample/login_page.dart';
import 'package:flutter_sample/main.dart';

import 'addpost_page.dart';

class ChatPage extends ConsumerWidget {
  @override
  Widget build(BuildContext context, WidgetRef ref) {
    final User user = ref.watch(userProvider)!;
    final AsyncValue<QuerySnapshot> asyncPostsQuery = ref.watch(postsQueryProvider);

    return Scaffold(
      appBar: AppBar(
        title: const Text("チャット"),
        actions: [
          IconButton(
            icon: const Icon(Icons.close),
            onPressed: () async {
              await FirebaseAuth.instance.signOut();
              await Navigator.of(context).pushReplacement(MaterialPageRoute(builder: (context) {
                return LoginPage();
              }));
            },
          )
        ],
      ),
      body: Column(
        children: [
          Container(
            padding: const EdgeInsets.all(8),
            child: Text("${user.email}"),
          ),
          Expanded(
            child: asyncPostsQuery.when(
              data: (QuerySnapshot query) {
                return ListView(
                  children: query.docs.map((document) {
                    return Card(
                      child: ListTile(
                        title: Text(document["text"]),
                        subtitle: Text(document["email"]),
                        trailing: document["email"] == user.email
                            ? IconButton(
                                icon: Icon(Icons.delete),
                                onPressed: () async {
                                  await FirebaseFirestore.instance.collection("posts").doc(document.id).delete();
                                },
                              )
                            : null,
                      ),
                    );
                  }).toList(),
                );
              },
              loading: () {
                return Center(child: Text("Loading..."));
              },
              error: (error, stackTrace) {
                return Center(child: Text("Error: $error"));
              },
            ),
          ),
        ],
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: () async {
          await Navigator.of(context).pushReplacement(MaterialPageRoute(builder: (context) {
            return AddPostPage();
          }));
        },
        child: const Icon(Icons.add),
      ),
    );
  }
}
