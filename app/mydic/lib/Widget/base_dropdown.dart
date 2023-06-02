import 'package:flutter/material.dart';

class BaseDropDown extends StatefulWidget {
  const BaseDropDown({super.key});

  @override
  State<BaseDropDown> createState() => _BaseDropDownState();
}

class _BaseDropDownState extends State<BaseDropDown> {
  String isSelectedValue = "あ";

  @override
  Widget build(BuildContext context) {
    return Container(
        padding: const EdgeInsets.only(left: 15, right: 20),
        decoration: BoxDecoration(color: Theme.of(context).primaryColor, borderRadius: const BorderRadius.all(Radius.circular(5))),
        child: DropdownButtonFormField<String>(
          icon: const Icon(
            Icons.arrow_drop_down,
            color: Colors.white,
          ),
          dropdownColor: Theme.of(context).primaryColor,
          style: const TextStyle(color: Colors.white, fontSize: 18),
          isExpanded: true,
          decoration: const InputDecoration(
            labelText: 'Deck',
            labelStyle: TextStyle(color: Colors.white),
            enabledBorder: InputBorder.none,
            focusedBorder: InputBorder.none,
            focusColor: Colors.white,
          ),
          items: const [
            DropdownMenuItem(
              value: 'あ',
              child: Text('あ'),
            ),
            DropdownMenuItem(
              value: 'い',
              child: Text('い'),
            ),
            DropdownMenuItem(
              value: 'う',
              child: Text('う'),
            ),
            DropdownMenuItem(
              value: 'え',
              child: Text('え'),
            ),
            DropdownMenuItem(
              value: 'お',
              child: Text('お'),
            ),
          ],
          value: isSelectedValue,
          onChanged: (String? value) {
            setState(() {
              isSelectedValue = value!;
            });
          },
        ));
  }
}
