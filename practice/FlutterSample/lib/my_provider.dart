import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:riverpod_annotation/riverpod_annotation.dart';

part 'my_provider.g.dart';

@riverpod
final infoTextProvider = StateProvider.autoDispose((ref) {
  return "";
});
