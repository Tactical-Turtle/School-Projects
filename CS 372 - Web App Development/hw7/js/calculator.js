function doNothing() {}

function calculate()
{
    let getNumber1;
    getNumber1 = document.getElementById("number1").value;
    getNumber1 = getNumber1*1;

    let getOperator;
    getOperator = document.getElementById("operator").value;

    let getNumber2;
    getNumber2 = document.getElementById("number2").value;
    getNumber2 = getNumber2*1;

    let result;
    switch(getOperator) 
    {
        case "+":
            result = getNumber1 + getNumber2;
            break;
        case "-":
            result = getNumber1 - getNumber2;
            break;
        case "*":
            result = getNumber1 * getNumber2;
            break;
        case "/":
            result = getNumber1 / getNumber2;
            break;
    }

    if (getOperator == "%")
    {
        var remainder1 = (getNumber1 % 1);
        var remainder2 = (getNumber2 % 1);

        if (remainder1 === 0 && remainder2 === 0)
        {
            result = getNumber1 % getNumber2;
        }

        else
        {
            document.getElementById("result").value = "Numbers are not integers";
        }
    }

    document.getElementById("result").value = result.toString();
}

document.getElementById("calculate-button").addEventListener("click", calculate);

