package com.example;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.mariuszgromada.math.mxparser.License;

public class PlotApp extends Application {

    private final ComboBox<FunctionType> functionTypeBox = new ComboBox<>();
    private final ComboBox<DifferentiationType> differentiationTypeBox = new ComboBox<>();

    private final TextField expressionField = new TextField("exp(-x^2)*sin(x)");
    private final TextField parameterAField = new TextField("1.0");
    private final TextField csvField = new TextField("data.csv");
    private final TextField startField = new TextField("-5.0");
    private final TextField endField = new TextField("5.0");
    private final TextField stepField = new TextField("0.1");

    private final NumberAxis xAxis = new NumberAxis();
    private final NumberAxis yAxis = new NumberAxis();
    private final LineChart<Number, Number> chart = new LineChart<>(xAxis, yAxis);

    @Override
    public void start(Stage stage) {
        License.iConfirmNonCommercialUse("Student project");

        functionTypeBox.getItems().addAll(FunctionType.values());
        functionTypeBox.setValue(FunctionType.STRING);

        differentiationTypeBox.getItems().addAll(DifferentiationType.values());
        differentiationTypeBox.setValue(DifferentiationType.NUMERICAL);

        chart.setTitle("Графік функції та похідної");
        chart.setCreateSymbols(false);
        xAxis.setLabel("X");
        yAxis.setLabel("Y");

        GridPane form = new GridPane();
        form.setHgap(10);
        form.setVgap(10);
        form.setPadding(new Insets(10));

        form.add(new Label("Тип функції:"), 0, 0);
        form.add(functionTypeBox, 1, 0);

        form.add(new Label("Тип похідної:"), 0, 1);
        form.add(differentiationTypeBox, 1, 1);

        form.add(new Label("Формула:"), 0, 2);
        form.add(expressionField, 1, 2);

        form.add(new Label("Параметр a:"), 0, 3);
        form.add(parameterAField, 1, 3);

        form.add(new Label("CSV-файл:"), 0, 4);
        form.add(csvField, 1, 4);

        form.add(new Label("Початок:"), 0, 5);
        form.add(startField, 1, 5);

        form.add(new Label("Кінець:"), 0, 6);
        form.add(endField, 1, 6);

        form.add(new Label("Крок:"), 0, 7);
        form.add(stepField, 1, 7);

        Button plotButton = new Button("Побудувати");
        Button clearButton = new Button("Очистити");

        plotButton.setOnAction(event -> plotFunction());
        clearButton.setOnAction(event -> chart.getData().clear());

        HBox buttons = new HBox(10, plotButton, clearButton);
        buttons.setAlignment(Pos.CENTER_LEFT);

        VBox root = new VBox(10, form, buttons, chart);
        root.setPadding(new Insets(10));

        updateFieldAvailability();
        functionTypeBox.setOnAction(e -> updateFieldAvailability());

        Scene scene = new Scene(root, 1000, 700);
        stage.setTitle("Function Plotter");
        stage.setScene(scene);
        stage.show();
    }

    private void updateFieldAvailability() {
        FunctionType type = functionTypeBox.getValue();

        boolean isString = type == FunctionType.STRING;
        boolean isAnalytical2 = type == FunctionType.ANALYTICAL_2;
        boolean isCsv = type == FunctionType.CSV;

        expressionField.setDisable(!isString);
        parameterAField.setDisable(!(isString || isAnalytical2));
        csvField.setDisable(!isCsv);

        if (type != FunctionType.STRING) {
            differentiationTypeBox.setValue(DifferentiationType.NUMERICAL);
            differentiationTypeBox.setDisable(true);
        } else {
            differentiationTypeBox.setDisable(false);
        }
    }

    private void plotFunction() {
        try {
            FunctionParameters parameters = readParameters();

            Function function = FunctionFactory.create(parameters);
            Differentiator differentiator = DifferentiatorFactory.create(parameters);

            XYChart.Series<Number, Number> functionSeries = new XYChart.Series<>();
            functionSeries.setName("f(x)");

            XYChart.Series<Number, Number> derivativeSeries = new XYChart.Series<>();
            derivativeSeries.setName("f'(x)");

            for (double x = parameters.getXStart(); x <= parameters.getXEnd() + 1e-9; x += parameters.getStep()) {
                double roundedX = Math.round(x / parameters.getStep()) * parameters.getStep();

                double fx = function.evaluate(roundedX);
                double dfx = differentiator.differentiate(function, roundedX);

                functionSeries.getData().add(new XYChart.Data<>(roundedX, fx));
                derivativeSeries.getData().add(new XYChart.Data<>(roundedX, dfx));
            }

            chart.getData().clear();
            chart.getData().add(functionSeries);
            chart.getData().add(derivativeSeries);

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Помилка");
            alert.setHeaderText("Не вдалося побудувати графік");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    private FunctionParameters readParameters() {
        FunctionParameters parameters = new FunctionParameters();

        parameters.setFunctionType(functionTypeBox.getValue());
        parameters.setDifferentiationType(differentiationTypeBox.getValue());
        parameters.setExpression(expressionField.getText().trim());
        parameters.setA(Double.parseDouble(parameterAField.getText().trim()));
        parameters.setCsvFilePath(csvField.getText().trim());
        parameters.setXStart(Double.parseDouble(startField.getText().trim()));
        parameters.setXEnd(Double.parseDouble(endField.getText().trim()));
        parameters.setStep(Double.parseDouble(stepField.getText().trim()));

        if (parameters.getStep() <= 0) {
            throw new IllegalArgumentException("Крок має бути більше 0");
        }
        if (parameters.getXStart() >= parameters.getXEnd()) {
            throw new IllegalArgumentException("Початок інтервалу має бути меншим за кінець");
        }

        return parameters;
    }

    public static void main(String[] args) {
        launch(args);
    }
}