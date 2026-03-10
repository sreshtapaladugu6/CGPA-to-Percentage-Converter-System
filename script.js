// Dark Mode Toggle
// CO4: DOM manipulation and JavaScript interactivity for UI theme switching
const darkModeToggle = document.getElementById('darkModeToggle');

darkModeToggle.addEventListener('click', () => { // CO3: JavaScript event handling
    document.body.classList.toggle('dark-mode'); // CO4: DOM class manipulation
    
    if (document.body.classList.contains('dark-mode')) { // CO3: Conditional logic
        darkModeToggle.textContent = '☀️ Light'; // CO4: Updating DOM content dynamically
    } else {
        darkModeToggle.textContent = '🌙 Dark'; // CO4: Updating DOM text
    }
});

// Tab Switching
// CO4: DOM selection for tab-based UI interaction
const tabBtns = document.querySelectorAll('.tab-btn');
const tabPanels = document.querySelectorAll('.tab-panel');

tabBtns.forEach(btn => { // CO3: JavaScript loop and function usage
    btn.addEventListener('click', () => { // CO4: Event listener for UI interaction
        const tabName = btn.getAttribute('data-tab'); // CO4: Accessing HTML attributes
        
        // Remove active class from all buttons and panels
        tabBtns.forEach(b => b.classList.remove('active')); // CO4: DOM class manipulation
        tabPanels.forEach(p => p.classList.remove('active')); // CO4: DOM class manipulation
        
        // Add active class to clicked button and corresponding panel
        btn.classList.add('active'); // CO4
        document.getElementById(tabName).classList.add('active'); // CO4: Dynamic tab display
    });
});

// Calculate CGPA Tab
// CO3: JavaScript data structure (array of objects)
let subjects = [{ name: '', grade: '', credits: '' }];

function renderSubjects() { // CO3: JavaScript function implementation
    const container = document.getElementById('subjectsContainer'); // CO4: DOM element access
    container.innerHTML = ''; // CO4: Updating DOM content
    
    subjects.forEach((subject, index) => { // CO3: Array iteration
        const subjectDiv = document.createElement('div'); // CO4: Creating DOM elements dynamically
        subjectDiv.className = 'subject-box';
        
        subjectDiv.innerHTML = `
            <div class="subject-header">
                <h3>Subject ${index + 1}</h3>
                ${subjects.length > 1 ? `<button class="remove-btn" onclick="removeSubject(${index})">✕ Remove</button>` : ''}
            </div>
            <div class="subject-inputs">
                <input type="text" placeholder="Subject name (optional)" value="${subject.name}" onchange="updateSubject(${index}, 'name', this.value)">
                <input type="number" placeholder="Grade (0-10) *" value="${subject.grade}" onchange="updateSubject(${index}, 'grade', this.value)" step="0.01" min="0" max="10">
                <input type="number" placeholder="Credits (optional)" value="${subject.credits}" onchange="updateSubject(${index}, 'credits', this.value)" step="1" min="1">
            </div>
        `;
        
        container.appendChild(subjectDiv); // CO4: Inserting element into DOM
    });
}

function updateSubject(index, field, value) { 
    subjects[index][field] = value; // CO3: Updating object data inside array
}

function removeSubject(index) {
    if (subjects.length > 1) { // CO3: Conditional logic
        subjects.splice(index, 1); // CO3: Array manipulation
        renderSubjects(); // CO3/CO4: Re-render UI after data change
    }
}

// CO4: Event listener for dynamically adding subject fields
document.getElementById('addSubject').addEventListener('click', () => {
    subjects.push({ name: '', grade: '', credits: '' }); // CO3: Adding object to array
    renderSubjects(); // CO4: Updating DOM
});

document.getElementById('calculateCGPA').addEventListener('click', () => {
    let totalPoints = 0;
    let totalCredits = 0;
    
    for (let subject of subjects) { // CO3: Loop through array
        const grade = parseFloat(subject.grade); // CO3: Number conversion
        const credits = subject.credits ? parseFloat(subject.credits) : 1;
        
        if (isNaN(grade) || grade < 0 || grade > 10) { // CO3: Input validation
            showResult('cgpaResult', 'Invalid grade points', '', 'purple');
            return;
        }
        
        if (!subject.credits || credits > 0) {
            totalPoints += grade * credits; // CO3: Mathematical calculation
            totalCredits += credits;
        } else {
            showResult('cgpaResult', 'Invalid credits', '', 'purple');
            return;
        }
    }
    
    if (totalCredits === 0) {
        showResult('cgpaResult', 'Enter at least one subject', '', 'purple');
        return;
    }
    
    const cgpa = (totalPoints / totalCredits).toFixed(2); // CO3: CGPA formula implementation
    const percentage = (cgpa * 9.5).toFixed(2); // CO3: CGPA to percentage conversion
    
    showResult('cgpaResult', cgpa, `Equivalent Percentage: ${percentage}%`, 'purple'); // CO4: Display result in DOM
});

// CGPA to Percentage Tab
document.getElementById('calculatePercent').addEventListener('click', () => {
    const cgpa = parseFloat(document.getElementById('cgpaInput').value); // CO4: Reading input value
    
    if (isNaN(cgpa) || cgpa < 0 || cgpa > 10) { // CO3: Input validation
        showResult('percentResult', 'Invalid CGPA', '', 'green');
        return;
    }
    
    const percentage = (cgpa * 9.5).toFixed(2); // CO3: Formula calculation
    showResult('percentResult', `${percentage}%`, '', 'green'); // CO4: Display result
});

// Percentage to CGPA Tab
document.getElementById('calculateCGPAFromPercent').addEventListener('click', () => {
    const percentage = parseFloat(document.getElementById('percentInput').value); // CO4
    
    if (isNaN(percentage) || percentage < 0 || percentage > 100) { // CO3: Validation
        showResult('cgpaFromPercentResult', 'Invalid Percentage', '', 'blue');
        return;
    }
    
    const cgpa = (percentage / 9.5).toFixed(2); // CO3: Percentage to CGPA formula
    showResult('cgpaFromPercentResult', `${cgpa} CGPA`, '', 'blue'); // CO4: Display result
});

// Helper function to show results
function showResult(elementId, value, subText, color) { // CO3: Reusable function
    const resultBox = document.getElementById(elementId); // CO4: DOM access
    
    resultBox.style.display = 'block'; // CO4: Modify element style
    resultBox.className = `result-box ${color}`; // CO4: Dynamic CSS class change
    
    resultBox.innerHTML = ` 
        <p class="result-label">Result:</p>
        <p class="result-value ${color}">${value}</p>
        ${subText ? `<p class="result-sub">${subText}</p>` : ''}
    `; // CO4: Dynamic DOM content rendering
}

// Initial render
renderSubjects(); // CO3/CO4: Initialize UI with default subject