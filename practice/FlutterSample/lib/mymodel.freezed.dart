// coverage:ignore-file
// GENERATED CODE - DO NOT MODIFY BY HAND
// ignore_for_file: type=lint
// ignore_for_file: unused_element, deprecated_member_use, deprecated_member_use_from_same_package, use_function_type_syntax_for_parameters, unnecessary_const, avoid_init_to_null, invalid_override_different_default_values_named, prefer_expression_function_bodies, annotate_overrides, invalid_annotation_target, unnecessary_question_mark

part of 'mymodel.dart';

// **************************************************************************
// FreezedGenerator
// **************************************************************************

T _$identity<T>(T value) => value;

final _privateConstructorUsedError = UnsupportedError(
    'It seems like you constructed your class using `MyClass._()`. This constructor is only meant to be used by freezed and you are not supposed to need it nor use it.\nPlease check the documentation here for more information: https://github.com/rrousselGit/freezed#custom-getters-and-methods');

MyModel _$MyModelFromJson(Map<String, dynamic> json) {
  return _MyModel.fromJson(json);
}

/// @nodoc
mixin _$MyModel {
  String get name => throw _privateConstructorUsedError;
  int get age => throw _privateConstructorUsedError;

  Map<String, dynamic> toJson() => throw _privateConstructorUsedError;
  @JsonKey(ignore: true)
  $MyModelCopyWith<MyModel> get copyWith => throw _privateConstructorUsedError;
}

/// @nodoc
abstract class $MyModelCopyWith<$Res> {
  factory $MyModelCopyWith(MyModel value, $Res Function(MyModel) then) =
      _$MyModelCopyWithImpl<$Res, MyModel>;
  @useResult
  $Res call({String name, int age});
}

/// @nodoc
class _$MyModelCopyWithImpl<$Res, $Val extends MyModel>
    implements $MyModelCopyWith<$Res> {
  _$MyModelCopyWithImpl(this._value, this._then);

  // ignore: unused_field
  final $Val _value;
  // ignore: unused_field
  final $Res Function($Val) _then;

  @pragma('vm:prefer-inline')
  @override
  $Res call({
    Object? name = null,
    Object? age = null,
  }) {
    return _then(_value.copyWith(
      name: null == name
          ? _value.name
          : name // ignore: cast_nullable_to_non_nullable
              as String,
      age: null == age
          ? _value.age
          : age // ignore: cast_nullable_to_non_nullable
              as int,
    ) as $Val);
  }
}

/// @nodoc
abstract class _$$_MyModelCopyWith<$Res> implements $MyModelCopyWith<$Res> {
  factory _$$_MyModelCopyWith(
          _$_MyModel value, $Res Function(_$_MyModel) then) =
      __$$_MyModelCopyWithImpl<$Res>;
  @override
  @useResult
  $Res call({String name, int age});
}

/// @nodoc
class __$$_MyModelCopyWithImpl<$Res>
    extends _$MyModelCopyWithImpl<$Res, _$_MyModel>
    implements _$$_MyModelCopyWith<$Res> {
  __$$_MyModelCopyWithImpl(_$_MyModel _value, $Res Function(_$_MyModel) _then)
      : super(_value, _then);

  @pragma('vm:prefer-inline')
  @override
  $Res call({
    Object? name = null,
    Object? age = null,
  }) {
    return _then(_$_MyModel(
      name: null == name
          ? _value.name
          : name // ignore: cast_nullable_to_non_nullable
              as String,
      age: null == age
          ? _value.age
          : age // ignore: cast_nullable_to_non_nullable
              as int,
    ));
  }
}

/// @nodoc
@JsonSerializable()
class _$_MyModel implements _MyModel {
  const _$_MyModel({required this.name, required this.age});

  factory _$_MyModel.fromJson(Map<String, dynamic> json) =>
      _$$_MyModelFromJson(json);

  @override
  final String name;
  @override
  final int age;

  @override
  String toString() {
    return 'MyModel(name: $name, age: $age)';
  }

  @override
  bool operator ==(dynamic other) {
    return identical(this, other) ||
        (other.runtimeType == runtimeType &&
            other is _$_MyModel &&
            (identical(other.name, name) || other.name == name) &&
            (identical(other.age, age) || other.age == age));
  }

  @JsonKey(ignore: true)
  @override
  int get hashCode => Object.hash(runtimeType, name, age);

  @JsonKey(ignore: true)
  @override
  @pragma('vm:prefer-inline')
  _$$_MyModelCopyWith<_$_MyModel> get copyWith =>
      __$$_MyModelCopyWithImpl<_$_MyModel>(this, _$identity);

  @override
  Map<String, dynamic> toJson() {
    return _$$_MyModelToJson(
      this,
    );
  }
}

abstract class _MyModel implements MyModel {
  const factory _MyModel({required final String name, required final int age}) =
      _$_MyModel;

  factory _MyModel.fromJson(Map<String, dynamic> json) = _$_MyModel.fromJson;

  @override
  String get name;
  @override
  int get age;
  @override
  @JsonKey(ignore: true)
  _$$_MyModelCopyWith<_$_MyModel> get copyWith =>
      throw _privateConstructorUsedError;
}
