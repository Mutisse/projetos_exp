function createAutoIncrement() {
    let count = 0;
  
    return function() {
      return ++count;
    };
  }
  
  // Uso da função de auto incremento
  const increment = createAutoIncrement();
  
  console.log("E-"+increment()); // 1
  
  