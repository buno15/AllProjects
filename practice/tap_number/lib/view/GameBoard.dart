import 'dart:async';

import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:tap_number/provider/game_view_model.dart';

class GameBoard extends ConsumerStatefulWidget {
  const GameBoard({Key? key}) : super(key: key);

  @override
  _GameBoardState createState() => _GameBoardState();
}

class _GameBoardState extends ConsumerState<GameBoard> {
  int _countdown = 3;
  Timer? _timer;

  @override
  void initState() {
    super.initState();
    _timer = Timer.periodic(const Duration(seconds: 1), (timer) {
      if (_countdown > 0) {
        setState(() {
          _countdown--;
        });
      } else {
        _timer?.cancel();
        setState(() {
        });
      }
    });
  }

  @override
  void dispose() {
    _timer?.cancel();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    final gameState = ref.watch(gameViewModelProvider);

    if (_countdown > 0) {
      return Scaffold(
        appBar: AppBar(
          title: const Text('Tap Number'),
        ),
        body: Center(
          child: Text('Game starts in $_countdown...'),
        ),
      );
    }

    return gameState.when(
      data: (data) {
        final board = data.board;
        final time = data.time;
        return Scaffold(
          appBar: AppBar(
            title: const Text('Tap Number'),
          ),
          body: Center(
            child: Column(
              mainAxisAlignment: MainAxisAlignment.center,
              children: [
                Text('Time: $time'),
                const SizedBox(height: 16),
                for (final row in board)
                  Row(
                    mainAxisAlignment: MainAxisAlignment.center,
                    children: [
                      for (final number in row)
                        Padding(
                          padding: const EdgeInsets.all(8.0),
                          child: TextButton(
                            onPressed: () {},
                            child: Text('$number'),
                          ),
                        ),
                    ],
                  ),
              ],
            ),
          ),
        );
      },
      loading: () => const CircularProgressIndicator(),
      error: (e, stack) => Text('Error: $e'),
    );
  }
}
