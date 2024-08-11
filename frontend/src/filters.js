const filters = [
    {
        id: 'category',
        name: 'Category',
        options: [
            { value: 'ScienceFiction', label: 'Science Fiction' },
            { value: 'Thriller', label: 'Thriller' },
            { value: 'Nature', label: 'Nature' },
            { value: 'Poetry', label: 'Poetry' },
            { value: 'SelfHelp', label: 'Self-Help' },
        ],
    },
    {
        id: 'prices',
        name: 'Price',
        options: [
            { value: '<10', label: '< 10$' },
            { value: '10-20', label: '10$ - 20$' },
            { value: '20-40', label: '20 - 40$' },
            { value: '40>', label: '40$ >' },
        ],
    },
]

export default filters;