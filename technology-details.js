document.addEventListener('DOMContentLoaded', function() {
    // Get all view more buttons
    const viewMoreButtons = document.querySelectorAll('.view-more-btn');
    
    // Add click event to each button
    viewMoreButtons.forEach(button => {
        button.addEventListener('click', function() {
            const techCard = this.closest('.tech-card');
            const fullDesc = techCard.querySelector('.full-desc');
            const shortDesc = techCard.querySelector('.short-desc');
            
            // Toggle the 'show' class for animation
            fullDesc.classList.toggle('show');
            
            // Toggle button text and icon
            if (fullDesc.classList.contains('show')) {
                this.innerHTML = 'View Less <i class="fas fa-chevron-up"></i>';
                shortDesc.style.display = 'none';
            } else {
                this.innerHTML = 'View More <i class="fas fa-chevron-down"></i>';
                shortDesc.style.display = 'block';
            }
            
            // Toggle active class for button
            this.classList.toggle('active');
            
            // Add card animation
            techCard.classList.add('animate-card');
            
            // Remove animation class after it completes to allow re-animation
            setTimeout(() => {
                techCard.classList.remove('animate-card');
            }, 600);
        });
    });
    
    // Add initial animations to cards when page loads
    const techCards = document.querySelectorAll('.tech-card');
    techCards.forEach((card, index) => {
        // Stagger the animations
        setTimeout(() => {
            card.classList.add('animate-card');
            
            // Remove animation class after it completes
            setTimeout(() => {
                card.classList.remove('animate-card');
            }, 600);
        }, index * 200);
    });
});
