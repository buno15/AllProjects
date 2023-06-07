import "package:flutter_test/flutter_test.dart";
import "package:integration_test/integration_test.dart";
import "package:integration_test/integration_test_driver.dart";
import "package:mydic/main.dart" as app;

void main() {
  IntegrationTestWidgetsFlutterBinding.ensureInitialized();

  group("end-to-end test", () {
    testWidgets("tap on floating action button", (widgetTester) async {
      app.main();

      await widgetTester.pumpAndSettle();
      final Finder fab = find.byTooltip("Increment");
      await widgetTester.tap(fab);

      await widgetTester.pumpAndSettle();
    });
  });
}
