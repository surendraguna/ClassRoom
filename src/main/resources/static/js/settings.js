const settingsTabs = document.querySelectorAll('.settings-tab');
const settingsSections = document.querySelectorAll('.settings-section');
const profileForm = document.getElementById('profileForm');
const accountForm = document.getElementById('accountForm');
const toggleBtn = document.querySelector('#toggleSidebar');
const navLinks = document.querySelector('.nav-links');


toggleBtn.addEventListener('click', () => {
    navLinks.classList.toggle('active');
});

settingsTabs.forEach(tab => {
    tab.addEventListener('click', () => {
        settingsTabs.forEach(t => t.classList.remove('active'));
        settingsSections.forEach(s => s.classList.remove('active'));
        tab.classList.add('active');
        const sectionId = tab.dataset.tab;
        document.getElementById(sectionId).classList.add('active');
    });
});

profileForm.addEventListener('submit', (e) => {
    e.preventDefault();
    
    const formData = {
        fullname: document.getElementById('fullName').value,
        bio: document.getElementById('bio').value
    };

    fetch('/user/name', {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(formData)
    })
    .then(response => {
        if (response.ok) {
            showNotification('Profile updated successfully!');
        } else {
            showNotification('Failed to update profile!');
        }
    })
    .catch(error => {
        console.error('Error updating profile:', error);
        showNotification('Error updating profile!');
    });
});


accountForm.addEventListener('submit', (e) => {
    e.preventDefault();
    const currentPassword = document.getElementById('currentPassword').value;
    const newPassword = document.getElementById('newPassword').value;
    const confirmPassword = document.getElementById('confirmPassword').value;

    if (currentPassword.trim() === '' || newPassword.trim() === '' || confirmPassword.trim() === '') {
        showNotification('All fields are required!', 'error');
        return;
    }

    if (newPassword !== confirmPassword) {
        showNotification('New passwords do not match!', 'error');
        return;
    }

    const data = {
        currentPassword,
        newPassword
    };

    fetch('/user/password', {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    })
    .then(response => {
        if (response.ok) {
            showNotification('Password updated successfully!');
            accountForm.reset();
        } else {
            response.text().then(message => {
                showNotification(message, 'error');
            });
        }
    })
    .catch(error => console.error('Error updating password:', error));
});

function showNotification(message, type = 'success') {
    const notification = document.createElement('div');
    notification.className = `notification ${type}`;
    notification.textContent = message;
    
    Object.assign(notification.style, {
        position: 'fixed',
        top: '1rem',
        right: '1rem',
        padding: '1rem 2rem',
        backgroundColor: type === 'success' ? '#10B981' : '#EF4444',
        color: 'white',
        borderRadius: '0.5rem',
        boxShadow: '0 2px 4px rgba(0,0,0,0.1)',
        zIndex: 1000,
        animation: 'slideIn 0.3s ease-out'
    });

    document.body.appendChild(notification);

    setTimeout(() => {
        notification.style.animation = 'slideOut 0.3s ease-out';
        setTimeout(() => notification.remove(), 300);
    }, 3000);
}

const style = document.createElement('style');
style.textContent = `
    @keyframes slideIn {
        from { transform: translateX(100%); opacity: 0; }
        to { transform: translateX(0); opacity: 1; }
    }
    @keyframes slideOut {
        from { transform: translateX(0); opacity: 1; }
        to { transform: translateX(100%); opacity: 0; }
    }
`;
document.head.appendChild(style);

document.addEventListener('DOMContentLoaded', () => {
    fetch('/user/details', {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        }
    })
    .then(response => response.json())
    .then(data => {
        if (data) {
            document.getElementById('fullName').value = data.fullname;
            document.getElementById('bio').value = data.bio;
        } else {
            console.log("User data not found.");
        }
    })
    .catch(error => console.error('Error fetching profile:', error));
});