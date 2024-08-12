const filters = [
    {
        id: 'author',
        name: 'Author',
        asArray: true,
        options: [
            { value: 'Daniel Kahneman', label: 'Daniel Kahneman' },
            { value: 'Jason Fried', label: 'Jason Fried' },
            { value: 'Simon Scarrow', label: 'Simon Scarrow' }
        ],
    },    {
        id: 'category',
        name: 'Category',
        asArray: true,
        options: [
            { value: 'Science', label: 'Science' },
            { value: 'Thriller', label: 'Thriller' },
            { value: 'Nature', label: 'Nature' },
            { value: 'Poetry', label: 'Poetry' },
            { value: 'SelfHelp', label: 'Self-Help' },
        ],
    },
    {
        id: 'price',
        name: 'Price',
        asArray: false,
        options: [
            { value: '<10', label: '< 10$' },
            { value: '10-20', label: '10$ - 20$' },
            { value: '20-40', label: '20 - 40$' },
            { value: '40>', label: '40$ >' },
        ],
    },
]

export default filters;