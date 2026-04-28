// Hämt alla HTML- element som vi kommer behöva och lägg dem i variabler.
const todoForm = document.querySelector(".formStyle")
const todoInput = document.querySelector("#todo-input")
const todolist = document.querySelector("#todo-list")
const clearButton = document.querySelector("#clearBtn")

// Alla requests ska gå till backend
const API_URL = "http://localhost:8080/tasks"



// Function that renders a task from the backend into the UI
const addTodo = (task) => {
    const li = document.createElement("li") //
    const span = document.createElement("span")
    const checkbox = document.createElement("input")
    const deleteBtn = document.createElement("button")
    const div = document.createElement("div")

    // Display the task description from the backend
    span.textContent = task.description
    span.classList.add("todo-text")

    // Set checkbox state based on backend value
    checkbox.type = "checkbox" 
    checkbox.checked = task.completed
    checkbox.classList.add("customCheckbox") // Ger vår checkbox ett classnamn så vi kan styla den i CSS

    if (task.completed) {
    span.style.textDecoration = "line-through"
    }


    deleteBtn.textContent = "x"
    deleteBtn.classList.add("delete-button")

    div.appendChild(checkbox)
    div.appendChild(span)

    li.appendChild(div)
    li.appendChild(deleteBtn)

    todolist.appendChild(li) 

    // Update task in backend when checkbox changes
    checkbox.addEventListener("change", async () => {

    await fetch(`${API_URL}/${task.id}`, {
        method: "PUT",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            description: task.description,
            completed: checkbox.checked
        })
    })

    // Reload tasks from backend to sync UI
    loadTasks()
})


// Delete task from backend when clicking X
deleteBtn.addEventListener("click", async () => {

    await fetch(`${API_URL}/${task.id}`, {
        method: "DELETE"
    })

    // Reload tasks from backend
    loadTasks()
})
}
    


// Fetch all tasks from backend and render them in the UI
const loadTasks = async () => {

    // Send GET request to backend
    const response = await fetch(API_URL)

    // Convert response to JavaScript objects
    const tasks = await response.json()

    // Clear current list before re-rendering
    todolist.innerHTML = ""

    // Loop through all tasks and render them
    tasks.forEach(task => {
        addTodo(task)
    })
}

// Lyssna efter "submit" eventet som finns på formuläret.
todoForm.addEventListener("submit", async (event) => {
    event.preventDefault()

    const input = todoInput.value

    if (!input.trim()) {
        todoForm.reset()
        return
    }

    // Skicka till backend
    await fetch(API_URL, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            description: input,
            completed: false
        })
    })

    // Töm input
    todoForm.reset()

    // Hämta uppdaterad lista från backend
    loadTasks()
})

clearButton.addEventListener("click", () => {
  todolist.innerHTML = "";
});

// Load tasks automatically when page loads
window.addEventListener("DOMContentLoaded", loadTasks)