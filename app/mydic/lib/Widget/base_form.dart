import 'package:flutter/material.dart';

class BaseForm extends StatelessWidget {
  const BaseForm({super.key, required this.label});

  final String label;

  @override
  Widget build(BuildContext context) {
    return TextField(
      style: const TextStyle(fontSize: 20),
      decoration: InputDecoration(
          fillColor: Colors.white,
          filled: true,
          labelText: label,
          labelStyle: TextStyle(color: Theme.of(context).primaryColor),
          border: const OutlineInputBorder(),
          enabledBorder: OutlineInputBorder(borderSide: BorderSide(color: Theme.of(context).primaryColor, width: 2)),
          focusedBorder: OutlineInputBorder(borderSide: BorderSide(color: Theme.of(context).primaryColor, width: 2))),
    );
  }
}
