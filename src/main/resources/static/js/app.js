const courseGrid = document.querySelector('.course-grid');
const template = document.querySelector('#course-card-template');
const toggleBtn = document.querySelector('#toggleSidebar');
const navLinks = document.querySelector('.nav-links');


toggleBtn.addEventListener('click', () => {
    navLinks.classList.toggle('active');
});


function renderCourses(coursesData) {
    const courseGrid = document.querySelector('.course-grid'); 
    const template = document.getElementById('course-card-template'); 
    courseGrid.innerHTML = '';
    coursesData.forEach(course => {
        const card = template.content.cloneNode(true);

        card.querySelector('.thumbnail img').src = course.thumbnail;
        card.querySelector('.thumbnail img').alt = course.title;
        card.querySelector('.title').textContent = course.title;
        card.querySelector('.description').textContent = course.description;
        card.querySelector('.subject').textContent = course.subject;

        card.querySelector('.course-card').addEventListener('click', () => {
            const url = 'http://localhost:8080/classroom/player';
            fetch(url, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({ courseId: course.id })
            })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                window.location.href = `http://localhost:8080/classroom/player`;
            })
            .catch(error => console.error('Error:', error));
        });

        courseGrid.appendChild(card);
    });
}


async function fetchCourses() {
    try {
        const response = await fetch('http://localhost:8080/classroom/courses'); 
        const courses = await response.json();
        renderCourses(courses);
    } catch (error) {
        console.error('Error fetching courses:', error);
    }
}

document.addEventListener('DOMContentLoaded', fetchCourses);

window.addEventListener('resize', () => {
    if (window.innerWidth > 768) {
        navLinks.classList.remove('active');
    }
});