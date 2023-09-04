import 'package:freezed_annotation/freezed_annotation.dart';

part 'mymodel.freezed.dart';

part 'mymodel.g.dart';

// freezedでコード生成するために「@freezed」を記述
@freezed
class MyModel with _$MyModel {
  // withの後には「_$[class name]」の形式で記述

  // プロパティを指定
  const factory MyModel({
    required String name,
    required int age,
  }) = _MyModel;

  // json形式で受け取るためのコードを生成するために記述
  factory MyModel.fromJson(Map<String, dynamic> json) => _$MyModelFromJson(json);
}
