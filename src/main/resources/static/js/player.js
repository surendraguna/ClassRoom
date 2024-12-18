const playlist = document.querySelector('.playlist');
const mainVideo = document.getElementById('mainVideo');
const videoTitle = document.getElementById('videoTitle');
const videoDescription = document.getElementById('videoDescription');
const template = document.getElementById('video-item-template');
const toggleBtn = document.querySelector('#toggleSidebar');
const navLinks = document.querySelector('.nav-links');

toggleBtn.addEventListener('click', () => {
    navLinks.classList.toggle('active');
});

const courseId = document.getElementById('courseId').innerText;
console.log("found", courseId);

async function fetchVideos() {
    if (!courseId) {
        console.error('Course ID not found in URL');
        return;
    }

    try {
        const response = await fetch(`http://localhost:8080/classroom/player/${courseId}`); 
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        const videos = await response.json();
        renderPlaylist(videos);
        if (videos.length > 0) {
            playVideo(videos[0]); 
        }
    } catch (error) {
        console.error('Error fetching videos:', error);
    }
}

function renderPlaylist(videos) {
    playlist.innerHTML = '';

    videos.forEach((video, index) => {
        const item = template.content.cloneNode(true);

        item.querySelector('.thumbnail img').src = video.thumbnail;
        item.querySelector('.thumbnail img').alt = video.title;
        item.querySelector('.title').textContent = video.title;
        item.querySelector('.description').textContent = video.description;

        const videoItem = item.querySelector('.video-item');
        videoItem.dataset.videoId = video.id;

        if (index === 0) {
            videoItem.classList.add('active'); 
        }

        videoItem.addEventListener('click', () => playVideo(video));

        playlist.appendChild(item);
    });
}


function playVideo(video) {
    mainVideo.src = video.video_link; 
    mainVideo.play();
  
    videoTitle.textContent = video.title;
    videoDescription.textContent = video.description;

    const items = document.querySelectorAll('.video-item');
    items.forEach(item => {
        item.classList.remove('active');
        if (parseInt(item.dataset.videoId) === video.id) {
            item.classList.add('active');
        }
    });
}


document.addEventListener('DOMContentLoaded', () => {
    fetchVideos(); 
});

document.addEventListener('keydown', (e) => {
    if (e.code === 'Space' && document.activeElement !== mainVideo) {
        e.preventDefault();
        mainVideo.paused ? mainVideo.play() : mainVideo.pause();
    }
});
