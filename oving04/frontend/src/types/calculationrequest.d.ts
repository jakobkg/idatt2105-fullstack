type Operator = '+' | '-' | '*' | '/';

type CalculationRequest = {
    operands: Array<Number>,
    operator: Operator
}