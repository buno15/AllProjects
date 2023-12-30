import 'package:tap_number/model/game_state.dart';
import 'package:riverpod_annotation/riverpod_annotation.dart';

part 'game_view_model.g.dart';

@riverpod
class GameViewModel extends _$GameViewModel {
  GameViewModel() : super();

  @override
  FutureOr<GameState> build() async {
    return const GameState(
      board: [
        [0, 0, 0],
        [0, 0, 0],
        [0, 0, 0]
      ],
      time: 0,
    );
  }
}
