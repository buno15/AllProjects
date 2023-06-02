import 'package:flutter/material.dart';
import 'package:mydic/Widget/base_button.dart';
import 'package:mydic/Widget/base_dropdown.dart';
import 'package:mydic/Widget/base_form.dart';

class EditCard extends StatelessWidget {
  const EditCard({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Theme.of(context).scaffoldBackgroundColor,
      appBar: AppBar(
        title: const Text("Edit"),
        backgroundColor: Theme.of(context).primaryColor,
        shape: const RoundedRectangleBorder(borderRadius: BorderRadius.vertical(bottom: Radius.circular(30))),
        elevation: 2,
        actions: <Widget>[
          Padding(
            padding: const EdgeInsets.only(right: 10),
            child: IconButton(
              icon: const Icon(Icons.delete, color: Colors.white),
              onPressed: () {},
            ),
          )
        ],
      ),
      body: Column(
        children: const [
          Padding(
            padding: EdgeInsets.only(top: 20, left: 20, right: 20),
            child: BaseDropDown(),
          ),
          Padding(
            padding: EdgeInsets.only(top: 20, left: 20, right: 20),
            child: BaseForm(label: "Front"),
          ),
          Padding(
            padding: EdgeInsets.only(top: 20, left: 20, right: 20),
            child: BaseForm(label: "Back"),
          ),
          Padding(
            padding: EdgeInsets.only(top: 20, left: 20, right: 20),
            child: BaseForm(label: "Tags"),
          ),
          Padding(
            padding: EdgeInsets.only(top: 20, left: 20, right: 20),
            child: SizedBox(
              width: double.infinity,
              height: 50,
              child: BaseButton(label: "Edit", fontSize: 20),
            ),
          )
        ],
      ),
    );
  }
}
