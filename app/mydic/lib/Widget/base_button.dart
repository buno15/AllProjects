import 'package:flutter/material.dart';

class BaseButton extends StatelessWidget {
  const BaseButton({super.key, required this.label, required this.fontSize});

  final String label;
  final double fontSize;

  @override
  Widget build(BuildContext context) {
    return ElevatedButton(
      onPressed: () {},
      style: ElevatedButton.styleFrom(backgroundColor: Theme.of(context).primaryColor, textStyle: TextStyle(fontSize: fontSize)),
      child: Text(label),
    );
  }
}
