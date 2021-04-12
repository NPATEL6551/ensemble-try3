import numpy as np
from flask import Flask, request, jsonify, render_template, session
import pickle

app = Flask(__name__)
model = pickle.load(open('model1.pkl', 'rb'))

@app.route('/')
def home():
    return render_template('index.html')

@app.route('/predict',methods=['POST'])

def predict():
    sex = request.json['sex']
    age = request.json['age']
    currentSmoker = request.json['currentSmoker']
    cigsPerDay = request.json['cigsPerDay']
    BPMeds = request.json['BPMeds']
    prevalentStroke = request.json['prevalentStroke']
    prevalentHyp = request.json['prevalentHyp']
    diabetes = request.json['diabetes']
    totChol = request.json['totChol']
    sysBP = request.json['sysBP']
    diaBP = request.json['diaBP']
    BMI = request.json['BMI']
    heartRate = request.json['heartRate']
    glucose = request.json['glucose']

    final_features = [np.array([sex,age,currentSmoker,cigsPerDay,BPMeds,prevalentStroke,prevalentHyp,diabetes,totChol,sysBP,diaBP,BMI,heartRate,glucose])]
    prediction = model.predict(final_features)
    print(final_features)
    output = round(prediction[0], 2)
    return jsonify(output)


if __name__ == "__main__":
    app.run()
